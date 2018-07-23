package com.bubble.controller;

import com.bubble.Message.Message;
import com.bubble.bean.*;
import com.bubble.dao.BubblesMapper;
import com.bubble.dao.CommentMapper;
import com.bubble.dao.UserMapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.swagger.annotations.ApiOperation;
import okhttp3.*;
import okhttp3.RequestBody;
import org.apache.commons.codec.digest.DigestUtils;
import org.h2.expression.ConditionIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.security.Key;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 处理用户类接口
 **/
@RestController
@PropertySource("classpath:message.properties")
@RequestMapping(value = "/api/v1/", name = "用户API")
public class UserController {

    @Autowired
    UserMapper userMapper;

    private Message message = new Message();
    private Key key = KeyUtil.getKey();
    private OkHttpClient client = new OkHttpClient();
    private String token(String phone) {
        String key = phone + "stonymoon";
        return DigestUtils.md5Hex(key);
    }

    /**
     * 注册
     **/
    @RequestMapping(value = "user/create", method = RequestMethod.POST)
    public ResponseEntity<Message> 用户注册(@RequestParam String username, @RequestParam String password, @RequestParam String phone, @RequestParam String token) {
        if (!token.equals(token(phone))) {
            System.out.println("error!");
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        }
        User user = new User();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<User> list = userMapper.selectByExample(userExample);
        if (list.isEmpty()) {
            user.setUsername(username);
            user.setPhone(phone);
            user.setEmojicount(0);
            user.setImage("http://oupl6wdxc.bkt.clouddn.com/EIEFJILJELJEG");
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            userMapper.insert(user);
            UserExample uE = new UserExample();
            UserExample.Criteria c = uE.createCriteria();
            c.andPhoneEqualTo(phone);
            List<User> l = userMapper.selectByExample(uE);
            for (User u : l) {
                RequestBody requestBody = new FormBody.Builder()
                        .add("uid", u.getId().toString())
                        .add("username", username)
                        .add("phone", phone)
                        .add("geotable_id", "1000002164")
                        .add("ak", "A61df0d768beeecce052cc58283d84c2")
                        .add("latitude", "1.0")
                        .add("longitude", "1.0")
                        .add("url", "http://oupl6wdxc.bkt.clouddn.com/EIEFJILJELJEG")
                        .build();
                Request request = new Request.Builder()
                        .url("http://api.map.baidu.com/geodata/v4/poi/create")
                        .post(requestBody)
                        .build();
                Call call = client.newCall(request);
                try {
                    Response response = call.execute();
                    message.setMsg(1, response.body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    message.setMsg(0, e.toString());
                }
            }
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        } else {
            message.setMsg(0, "注册失败！");
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        }
    }

    /**
     * 登录
     **/
    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public ResponseEntity<Message> 用户登录(@RequestParam String phone, @RequestParam String password) {
        if (phone.equals("") || password.equals("")) {
            System.out.println("error!");
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        }
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 1000 * 60 * 60 * 24 * 365 * 4;// 4年的时间token过期
        Date time = new Date(expMillis);
        User user = new User();
        Gson gson = new Gson();
        user.setPhone(phone);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);

        List<User> list = userMapper.selectByExample(userExample);
        if (list.isEmpty()) {
            message.setMsg(0, "登录失败！手机号不存在！");
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        } else {
            for (User u : list) {
                if (BCrypt.checkpw(password, u.getPassword())) {
                    String jwtString = Jwts.builder().setSubject(gson.toJson(u)).signWith(SignatureAlgorithm.HS512, key).setExpiration(time).compact();
                    //System.out.println(jwtString);
                    TokenId tokenId = new TokenId();
                    tokenId.setId(u.getId());
                    tokenId.setToken(jwtString);
                    message.setMsg(1, "登陆成功！", tokenId);
                    return new ResponseEntity<Message>(message, HttpStatus.OK);
                } else {
                    message.setMsg(0, "登录失败！ 密码错误");
                    return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
                }
            }
        }
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    /**
     * 更新用户坐标
     */
    @RequestMapping(value = "user/updateLocate", method = RequestMethod.POST)
    public ResponseEntity<String> 更新用户坐标(@RequestParam String locationId, @RequestParam double latitude, @RequestParam double longitude) {
        RequestBody requestBody = new FormBody.Builder()
                .add("id", locationId)
                .add("geotable_id", "1000002164")
                .add("ak", "A61df0d768beeecce052cc58283d84c2")
                .add("latitude", String.valueOf(latitude))
                .add("longitude", String.valueOf(longitude))
                .build();
        Request request = new Request.Builder()
                .url("http://api.map.baidu.com/geodata/v4/poi/update")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        String re = "";
        try {
            Response response = call.execute();
            re = response.body().string();
            return new ResponseEntity<String>(re, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.FORBIDDEN);
        }
    }
    /**
     * 更新地图
     */
    @RequestMapping(value = "user/updateMap", method = RequestMethod.POST)
    public ResponseEntity<String> 刷新地图(@RequestParam double latitude, @RequestParam double longitude) {
        String bounds = String.format("%.2f", (longitude - 0.2)) + ","
                + String.format("%.2f", (latitude - 0.2)) + ";" +
                String.format("%.2f", (longitude + 0.2)) + "," + String.format("%.2f", (latitude + 0.2));
        String pageSize = "40";
        RequestBody requestBody = new FormBody.Builder()
                .add("bounds", bounds)
                .add("geotable_id", "1000002164")
                .add("ak", "A61df0d768beeecce052cc58283d84c2")
                .add("page_size", pageSize)
                .build();
        Request request = new Request.Builder()
                .url("http://api.map.baidu.com/geodata/v4/poi/list")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        String re = "";
        try {
            Response response = call.execute();
            re = response.body().string();
            return new ResponseEntity<String>(re, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 更新头像
     */
    @RequestMapping(value = "user/updateHead", method = RequestMethod.POST)
    public ResponseEntity<String> 刷新地图(@RequestParam String locationId, @RequestParam String url) {
        RequestBody requestBody = new FormBody.Builder()
                .add("id", locationId)
                .add("geotable_id", "1000002164")
                .add("ak", "A61df0d768beeecce052cc58283d84c2")
                .add("url", url)
                .build();
        Request request = new Request.Builder()
                .url("http://api.map.baidu.com/geodata/v4/poi/update")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        Response response = null;
        String re = "";
        try {
            response = call.execute();
            re = response.body().string();

        } catch (Exception e) {

            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(re, HttpStatus.OK);
    }

    /**
     * 上传头像
     **/
    @RequestMapping(value = "user/image/imageToken", method = RequestMethod.POST)
    public ResponseEntity<Message> 上传头像Token(@RequestParam String token, @RequestParam String name) {
        try {
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);//compactJws为jwt字符串
            Claims body = parseClaimsJws.getBody();//得到body后我们可以从body中获取我们需要的信息
            String ak = "zfTdGi-7kuIP0YH-FxFodCzd87-yBYg8io5z88VR";
            String sk = "HZ0aVM3_RwTYKlbJLshhWetbcgDJz9ApI8PoHw3r";
            Auth auth = Auth.create(ak, sk);
            long nowMillis = System.currentTimeMillis();
            StringMap putPolicy = new StringMap();
            putPolicy.put("mimeLimit", "image/jpeg;image/png")
                    .put("fsizeLimit", 5242880);
            String ut = auth.uploadTokenWithDeadline("blog", name, nowMillis + 1000 * 10, putPolicy, true);

            message.setMsg(1, "生成成功!", ut);
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        } catch (SignatureException | MalformedJwtException e) {
            // TODO: handle exception
            // don't trust the JWT!
            // jwt 解析错误
            message.setMsg(0, "认证失败!");
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        } catch (ExpiredJwtException e) {
            // TODO: handle exception
            // jwt 已经过期，在设置jwt的时候如果设置了过期时间，这里会自动判断jwt是否已经过期，如果过期则会抛出这个异常，我们可以抓住这个异常并作相关处理。
            message.setMsg(0, "认证过期!");
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            //任何错误。。。
            message.setMsg(0, "其他报错：" + e.toString());
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "user/image/imageUpload", method = RequestMethod.POST)
    public ResponseEntity<Message> 上传头像(@RequestParam String token, @RequestParam String url) {
        try {
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);//compactJws为jwt字符串
            Claims body = parseClaimsJws.getBody();//得到body后我们可以从body中获取我们需要的信息
            //比如 获取主题,当然，这是我们在生成jwt字符串的时候就已经存进来的
            String subject = body.getSubject();
            Gson gson = new Gson();
            User user = gson.fromJson(subject, User.class);
            User user1 = new User();
            user1.setId(user.getId());
            user1.setImage(url);
            userMapper.updateByPrimaryKeySelective(user1);
            message.setMsg(1, "上传成功!");
            return new ResponseEntity<Message>(message, HttpStatus.OK);
        } catch (SignatureException | MalformedJwtException e) {
            // TODO: handle exception
            // don't trust the JWT!
            // jwt 解析错误
            message.setMsg(0, "认证失败!");
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        } catch (ExpiredJwtException e) {
            // TODO: handle exception
            // jwt 已经过期，在设置jwt的时候如果设置了过期时间，这里会自动判断jwt是否已经过期，如果过期则会抛出这个异常，我们可以抓住这个异常并作相关处理。
            message.setMsg(0, "认证过期!");
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            //任何错误。。。
            message.setMsg(0, "其他报错：" + e.toString());
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        }
    }

    /**
     * click表情
     **/
    @RequestMapping(value = "user/emoji/count", method = RequestMethod.POST)
    public ResponseEntity<Message> 表情_数量(@RequestParam String token, @RequestParam int uid, @RequestParam int count) {
        try {
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);//compactJws为jwt字符串
            Claims body = parseClaimsJws.getBody();//得到body后我们可以从body中获取我们需要的信息
            //比如 获取主题,当然，这是我们在生成jwt字符串的时候就已经存进来的
            String subject = body.getSubject();
            User user = new User();
            user.setId(uid);
            int amount = userMapper.selectByPrimaryKey(uid).getEmojicount();
            amount += count;
            if (amount < 0) {
                message.setMsg(0, "数量不足!");
                return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
            } else {
                user.setEmojicount(amount);
                userMapper.updateByPrimaryKeySelective(user);
                message.setMsg(1, "成功!");
                return new ResponseEntity<Message>(message, HttpStatus.OK);
            }
        } catch (SignatureException | MalformedJwtException e) {
            // TODO: handle exception
            // don't trust the JWT!
            // jwt 解析错误
            message.setMsg(0, "认证失败!");
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        } catch (ExpiredJwtException e) {
            // TODO: handle exception
            // jwt 已经过期，在设置jwt的时候如果设置了过期时间，这里会自动判断jwt是否已经过期，如果过期则会抛出这个异常，我们可以抓住这个异常并作相关处理。
            message.setMsg(0, "认证过期!");
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            //任何错误。。。
            message.setMsg(0, "其他报错：" + e.toString());
            return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
        }
    }

    /**
     * 获取用户信息
     **/
    @ResponseBody
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> 获取用户信息(@PathVariable int id) {
        User user = userMapper.selectByPrimaryKey(id);
        HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        if (user == null) {
            message.setMsg(0, "未找到用户");
        } else {
            message.setMsg(1, "用户信息", user);
        }
        return new ResponseEntity<Message>(message, status);
    }
}