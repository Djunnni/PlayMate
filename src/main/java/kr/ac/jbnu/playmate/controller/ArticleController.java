package kr.ac.jbnu.playmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.jbnu.playmate.model.Article;
import kr.ac.jbnu.playmate.model.Class;
import kr.ac.jbnu.playmate.model.User;
import kr.ac.jbnu.playmate.service.impl.ArticleServiceImpl;
import kr.ac.jbnu.playmate.service.impl.UserServiceImpl;
import kr.ac.jbnu.playmate.util.MyAuthentication;

@Controller
public class ArticleController {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	ArticleServiceImpl articleService;
	
	
	@Secured("ROLE_USER")
	@GetMapping("/keyadd/{type}/{content}")
	public String addBoard(MyAuthentication auth,@PathVariable String type,@PathVariable String content,Model model) {
		if(articleService.addArticle(type, auth.getUser(),content)) {
			model.addAttribute(content, content);
		}
		return "classroom/classroom";
	}
	
	@Secured("ROLE_TEACHER")
	@GetMapping("/class/auth/{type}/{content}")
	public String authWrite(MyAuthentication auth,@PathVariable String type,@PathVariable String content,Model model) {
		User user = auth.getUser();
		Class myclass= user.getClassId();
		List<Article> articles= articleService.getArticles(myclass, type);

		model.addAttribute("articles", articles);
		return "classroom/classroom";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/class/{type}")
	@ResponseBody
	public List<Article> unAuthWrite(MyAuthentication auth,@PathVariable String type,Model model) {
		User user = auth.getUser();
		Class myclass= user.getClassId();
		List<Article> articles= articleService.getArticles(myclass, type);

		model.addAttribute("articles", articles);
		return articles;
	}
	
}
