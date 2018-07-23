package com.movie.control;

import com.movie.bean.*;
import com.movie.dao.CommentMapper;
import com.movie.dao.MovieMapper;
import com.movie.dao.UMCMapper;
import com.movie.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller

public class AdminController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    MovieMapper movieMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UMCMapper umcMapper;

    /**
     * 后台窗口
     * @param model
     * @param response
     * @return
     */
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String admin(Model model, HttpServletResponse response){

        return "admin-m";
    }

    /**
     * 后台首页界面
     * @param model
     * @return
     */
    @RequestMapping(value = "/welcome" , method = RequestMethod.GET)
    public String welcome(Model model){

        CommentExample commentExample = new CommentExample();
        int commentnum = commentMapper.countByExample(commentExample);
        MovieExample movieExample = new MovieExample();
        int movienum = movieMapper.countByExample(movieExample);
        UserExample userExample = new UserExample();
        int usernum = userMapper.countByExample(userExample);

        model.addAttribute("commentnum",commentnum);
        model.addAttribute("movienum",movienum);
        model.addAttribute("usernum",usernum);
        return "welcome";
    }

    /**
     * 新增电影表单提交
     * @param model
     * @return
     */
    @RequestMapping(value ="/movie-add" ,method = RequestMethod.GET)
    public String movieAddFrom(Model model){
        model.addAttribute("movie",new Movie());
        return "movie-add";
    }
    @RequestMapping(value ="/movie-add" ,method = RequestMethod.POST)
    public @ResponseBody Object movieAdd(@ModelAttribute Movie movie){
        HashMap<String,String> res = new HashMap<>();
        String title = movie.getTitle();
        String genres = movie.getGenres();
        String image = movie.getImage();
        String year = movie.getYear();
        Integer commentsCount = 0;
        Float average = movie.getAverage();
        String summary = movie.getSummary();
        if(title==null||genres==null||image==null||year==null||commentsCount==null||average==null||summary==null)
        {
            res.put("操作结果:","字段不能为空！插入失败！");
        }else{
            movieMapper.insert(movie);
            res.put("操作结果:","成功！");
        }
        return res;
    }

    /**
     * 电影列表获取、删除
     * @param model
     * @return
     */
    @RequestMapping(value ="/movie-list" ,method = RequestMethod.GET)
    public String GetMovieList(Model model){
        model.addAttribute("movie",movieMapper.selectByExample(new MovieExample()));
        return "movie-list";
    }
    @RequestMapping(value ="/api/moviedelet" ,method = RequestMethod.POST)
    public @ResponseBody Object deleteMovieList(HttpServletRequest request){
        int movieid = Integer.valueOf(request.getParameter("mid"));
        //删除电影的同时，要先去把外键的表先删除数据。
        UMCExample umcExample = new UMCExample();
        UMCExample.Criteria criteria =  umcExample.createCriteria();
        criteria.andMidEqualTo(movieid);
        umcMapper.deleteByExample(umcExample);
        movieMapper.deleteByPrimaryKey(movieid);
        HashMap<String,String> res = new HashMap<>();

        return res;
    }

    /**
     * 评论列表获取、删除
     * @param model
     * @return
     */
    @RequestMapping(value ="/feedback-list" ,method = RequestMethod.GET)
    public String GetCommentList(Model model){
        model.addAttribute("comment",commentMapper.selectByExample(new CommentExample()));
        return "feedback-list";
    }
    @RequestMapping(value ="/api/commentdelet" ,method = RequestMethod.POST)
    public @ResponseBody Object deleteComment(HttpServletRequest request){
        int commentid = Integer.valueOf(request.getParameter("cid"));

        UMCExample umcExample = new UMCExample();
        UMCExample.Criteria criteria =  umcExample.createCriteria();
        criteria.andCidEqualTo(commentid);
        umcMapper.deleteByExample(umcExample);

        commentMapper.deleteByPrimaryKey(commentid);
        HashMap<String,String> res = new HashMap<>();
        return res;
    }

    /**
     * 用户列表获取、删除
     * @param model
     * @return
     */
    @RequestMapping(value ="/member-list" ,method = RequestMethod.GET)
    public String GetMemberList(Model model){
        model.addAttribute("member",userMapper.selectByExample(new UserExample()));
        return "member-list";
    }
    @RequestMapping(value ="/api/userdelete" ,method = RequestMethod.POST)
    public @ResponseBody Object deleteUser(HttpServletRequest request){
        int userid = Integer.valueOf(request.getParameter("uid"));

        UMCExample umcExample = new UMCExample();
        UMCExample.Criteria criteria =  umcExample.createCriteria();
        criteria.andUidEqualTo(userid);
        umcMapper.deleteByExample(umcExample);

        userMapper.deleteByPrimaryKey(userid);
        HashMap<String,String> res = new HashMap<>();
        return res;
    }

    /**
     * 后台登录界面
     * @return
     */
    @RequestMapping(value ="/adminlogin", method = RequestMethod.GET)
    public String login() {
        return "adminlogin";
    }

}
