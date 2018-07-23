package com.bubble.controller;

import com.bubble.Message.Message;
import com.bubble.bean.Bubbles;
import com.bubble.bean.Comment;
import com.bubble.bean.KeyUtil;
import com.bubble.bean.User;
import com.bubble.dao.BubblesMapper;
import com.bubble.dao.CommentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * 处理泡泡类接口
 **/
@RestController
@PropertySource("classpath:message.properties")
@RequestMapping(value = "/api/v1/", name = "泡泡API")
public class BubblesController {
    @Autowired
    BubblesMapper bubblesMapper;
    @Autowired
    CommentMapper commentMapper;
    private Message message = new Message();
    private Key key = KeyUtil.getKey();
    /**
     * 分享
     **/
    @RequestMapping(value = "bubbles/share", method = RequestMethod.POST)
    public ResponseEntity<Message> 分享bubble(@RequestParam String token, @RequestParam String title, @RequestParam String content, @RequestParam String image,
                                            @RequestParam double latitude, @RequestParam double longitude, @RequestParam int anonymous, @RequestParam int type) {
        try {
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);//compactJws为jwt字符串
            Claims body = parseClaimsJws.getBody();//得到body后我们可以从body中获取我们需要的信息
            //比如 获取主题,当然，这是我们在生成jwt字符串的时候就已经存进来的
            String subject = body.getSubject();
            Gson gson = new Gson();
            User user = gson.fromJson(subject, User.class);
            Bubbles bubbles = new Bubbles();
            //System.out.println(subject);
            //OK, we can trust this JWT
            long nowMillis = System.currentTimeMillis();
            long expMillis = nowMillis + 1000 * 60 * 60 * 72;
            Date time = new Date(expMillis);
            bubbles.setUid(user.getId());
            bubbles.setLatitude(latitude);
            bubbles.setLongitude(longitude);
            bubbles.setContent(content);
            bubbles.setTitle(title);
            bubbles.setImage(image);
            bubbles.setDeadline(time);
            bubbles.setType(type);
            bubbles.setClick(0);
            bubbles.setAnonymous(anonymous);
            bubbles.setComments(0);
            bubblesMapper.insert(bubbles);
            message.setMsg(1, "记录成功！");
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
     * 未过期分享内容
     *
     * @return
     */
    @ApiOperation(value = "获取Bubble信息", notes = "用于地图上的气泡信息获取")
    @RequestMapping(value = "bubbles/map", method = RequestMethod.GET)
    public ResponseEntity<Message> 未过期bubbles() {
        try {
            List<Bubbles> list = bubblesMapper.selectNew();
            if (list.isEmpty()) {
                message.setMsg(0, "分享内容都已过期！");
                return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
            } else {
                message.setMsg(1, "返回内容：", list);
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
     * 获取指定用户分享内容
     *
     * @return
     */
    @RequestMapping(value = "bubbles/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> 根据用户id获取_forUser(@PathVariable int id) {
        try {
            List<Bubbles> list = bubblesMapper.selectUser(id);
            if (list.isEmpty()) {
                message.setMsg(0, "用户不存在!");
                return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
            } else {
                message.setMsg(1, "返回内容：", list);
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

    @RequestMapping(value = "bubbles/guest/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> 根据用户id获取_forGuest(@PathVariable int id) {
        try {
            List<Bubbles> list = bubblesMapper.selectUserA(id);
            if (list.isEmpty()) {
                message.setMsg(0, "用户不存在 或 该用户没有Bubbles");
                return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
            } else {
                message.setMsg(1, "返回内容：", list);
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
     * 根据信息id获取分享信息
     *
     * @return
     */
    @RequestMapping(value = "bubbles/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> 根据泡泡id获取(@PathVariable int id) {
        try {
            Bubbles bubble = bubblesMapper.selectByBid(id);
            if (bubble == null) {
                message.setMsg(0, "找不到该内容！");
                return new ResponseEntity<Message>(message, HttpStatus.EXPECTATION_FAILED);
            } else {
                message.setMsg(1, "内容：", bubble);
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
     * 点击增加时间
     *
     * @return
     */
    @RequestMapping(value = "bubbles/time/add", method = RequestMethod.POST)
    public ResponseEntity<Message> 点击增加分享时间(@RequestParam int id) {
        try {
            Bubbles bubbles = bubblesMapper.selectByPrimaryKey(id);
            if (bubbles == null) {
                message.setMsg(0, "未找到该帖子！");
                return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
            }
            long newtime = bubbles.getDeadline().getTime() + 1000 * 60 * 60;
            Date time = new Date(newtime);
            Bubbles bubble = new Bubbles();
            bubble.setDeadline(time);
            bubble.setId(bubbles.getId());
            bubble.setClick(bubbles.getClick() + 1);
            bubblesMapper.updateByPrimaryKeySelective(bubble);
            message.setMsg(1, "增加成功！");
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
     * 点击减少时间
     */
    @RequestMapping(value = "bubbles/time/reduce", method = RequestMethod.POST)
    public ResponseEntity<Message> 点击减少分享时间(@RequestParam int id) {
        try {
            Bubbles bubbles = bubblesMapper.selectByPrimaryKey(id);
            if (bubbles == null) {
                message.setMsg(0, "未找到该帖子！");
                return new ResponseEntity<Message>(message, HttpStatus.FORBIDDEN);
            }
            long newtime = bubbles.getDeadline().getTime() - 1000 * 60 * 60;
            Date time = new Date(newtime);
            Bubbles bubble = new Bubbles();
            bubble.setDeadline(time);
            bubble.setId(bubbles.getId());
            bubble.setClick(bubbles.getClick() + 1);
            bubblesMapper.updateByPrimaryKeySelective(bubble);
            message.setMsg(1, "减少成功！");
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
     * 评论内容
     */
    @ResponseBody
    @RequestMapping(value = "bubbles/comment", method = RequestMethod.POST)
    public ResponseEntity<Message> 评论内容(@RequestParam int pid, @RequestParam String token, @RequestParam String content) {
        try {
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);//compactJws为jwt字符串
            Claims body = parseClaimsJws.getBody();//得到body后我们可以从body中获取我们需要的信息
            //比如 获取主题,当然，这是我们在生成jwt字符串的时候就已经存进来的
            String subject = body.getSubject();
            Gson gson = new Gson();
            User user = gson.fromJson(subject, User.class);
            int uid = user.getId();
            Comment comment = new Comment();
            Bubbles bubble1 = new Bubbles();
            Bubbles bubble2 = bubblesMapper.selectByPrimaryKey(pid);
            bubble1.setId(pid);
            bubble1.setComments(bubble2.getComments() + 1);
            bubblesMapper.updateByPrimaryKeySelective(bubble1);
            comment.setPid(pid);
            comment.setUid(uid);
            comment.setContent(content);
            commentMapper.insert(comment);
            message.setMsg(1, "评论成功！");
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
     * 获取评论内容
     */
    @ResponseBody
    @RequestMapping(value = "bubbles/comment/{id}/{pageNum}", method = RequestMethod.GET)
    public ResponseEntity<Message> 获取评论内容(@PathVariable int id, @PathVariable int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Comment> list = commentMapper.selectById(id);
        PageInfo<Comment> p = new PageInfo<Comment>(list);
        message.setMsg(1, "评论：", p);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
}


