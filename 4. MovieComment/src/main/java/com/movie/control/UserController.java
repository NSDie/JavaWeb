package com.movie.control;

import com.movie.bean.*;
import com.movie.dao.CommentMapper;
import com.movie.dao.MovieMapper;
import com.movie.dao.UMCMapper;
import com.movie.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;

@Controller

public class UserController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MovieMapper movieMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UMCMapper umcMapper;

    int movieId;

    @RequestMapping(value ={"/","/index"})
    public String index(Model model,HttpServletRequest request) {
        if (request.getSession().getAttribute("username") != null) {
            String username = request.getSession().getAttribute("username").toString();
            if (username.equals("")) {
                model.addAttribute("username", null);

            } else {
                model.addAttribute("username", username);
            }

        }else{
            model.addAttribute("username", null);
        }
        // 获取全部电影
        MovieExample movieExample = new MovieExample();
        List<Movie> movieList= movieMapper.selectByExampleWithBLOBs(movieExample);
        model.addAttribute("movie",movieList);

        return "index";
    }

    @RequestMapping("/userlogout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login.html";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
    @RequestMapping(value = "/api/login" , method = RequestMethod.POST)
    public @ResponseBody Object login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HashMap<String,String> res = new HashMap<String ,String>();

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(userExample);

        if(list.isEmpty()){
            res.put("stateCode","0");
            res.put("msg","用户名不存在！");
        }
        else{
            for(User u:list){
                if(!u.getPassword().equals(password)) {
                    res.put("stateCode","0");
                    res.put("msg", "密码错误!");
                }
                else{
                    request.getSession().setAttribute("username",username);
                    request.getSession().setAttribute("userid",u.getUid());
                    request.getSession().setMaxInactiveInterval(3600);
                    res.put("stateCode","1");
                    res.put("msg","登录成功！ 跳转中....");
                }
            }
        }


            return res;

    }

    @RequestMapping(value = "/register")
    public String register(Model model) {
        return "register";
    }
    //负责处理register的请求
    @RequestMapping(value = "/api/register" , method = RequestMethod.POST)
    public @ResponseBody Object registerCheck(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HashMap<String,String> res = new HashMap<String ,String>();
        User user = new User();

        // select * from user where username = " "
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(userExample);

        if(list.isEmpty()){
            // insert into user (username,password,comments);
            user.setUsername(username);
            user.setPassword(password);
            user.setCommentsCount(0);
            userMapper.insert(user);
            System.out.println(username+"注册成功！");

            res.put("msg","注册成功！ 跳转中 ...");
            res.put("stateCode","1");
        }else{
            res.put("msg","注册失败！ 用户名已存在！");
            res.put("stateCode","0");
        }

        return res;
    }

    @RequestMapping(value="/subject/{id}" ,method = RequestMethod.GET)
    public String 电影详情(Model model,@PathVariable int id,HttpServletResponse response,HttpServletRequest request)throws IOException {
        if (request.getSession().getAttribute("username") != null) {
            String username = request.getSession().getAttribute("username").toString();
            if (username.equals("")) {
                model.addAttribute("username", null);
            } else {
                model.addAttribute("username", username);
            }

        }else{
            model.addAttribute("username", null);
        }
        movieId = id;
        Movie movie = movieMapper.selectByPrimaryKey(id);
        List<UMC> commentlist = umcMapper.selectByMovieId(id);
        model.addAttribute("commentList",commentlist);
        model.addAttribute("comment",new Comment());
        if (movie == null){
            response.sendRedirect( "/index");
        }else{
            model.addAttribute("movie",movie);
        }
        return "subject";
    }
    @RequestMapping(value="/subject/comment" ,method = RequestMethod.POST)
    public void 电影评论(@ModelAttribute Comment comment,HttpServletResponse response,HttpServletRequest request) throws IOException{

        int userid=0;
        int cid=0;
        if(request.getSession().getAttribute("userid")!=null){
            userid= Integer.parseInt(request.getSession().getAttribute("userid").toString());
        }
        Movie movie = movieMapper.selectByPrimaryKey(movieId);
        movie.setCommentsCount(movie.getCommentsCount() + 1 );
        movieMapper.updateByPrimaryKeySelective(movie);
        commentMapper.insertSelective(comment);

        cid = comment.getCid();

        UMC umc = new UMC();
        if(cid >=0 && userid>=0) {
            umc.setCid(cid);
            umc.setUid(userid);
            umc.setMid(movieId);
            umcMapper.insert(umc);
        }
        response.sendRedirect( "/subject/"+movieId);
    }

    @RequestMapping(value = "/search/{title}",method = RequestMethod.GET)
    public String 电影搜索(Model model, @PathVariable String title){

        String result = '%'+title+'%';
        List<Movie> movieList= movieMapper.selectByTitle(result);
        if(movieList.isEmpty())
        {
            model.addAttribute("msg","找不到您想要的电影！");
        }else{
            model.addAttribute("msg","搜索到的结果：");
        }
        model.addAttribute("movie",movieList);
        return "search";
    }
}

