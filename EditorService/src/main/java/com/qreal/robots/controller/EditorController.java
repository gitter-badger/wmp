package com.qreal.robots.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.qreal.robots.common.loaders.TypesLoader;
import com.qreal.robots.common.utils.AuthenticatedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
/**
 * Main controller of Editor service.
 * Pages: editor
 */
public class EditorController {

    private static final Logger logger = LoggerFactory.getLogger(EditorController.class);

    private TypesLoader typesLoader;

    public EditorController() {
        this.typesLoader = new TypesLoader();
    }

    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView index() {
        logger.info("User {} requested editor", AuthenticatedUser.getUserName());
        return new ModelAndView("editor/editor");
    }

    @ResponseBody
    @RequestMapping(value = "getTypes/", method = RequestMethod.POST)
    public JsonNode getTypes(@RequestParam(value = "kit") String kit) throws IOException {
        return typesLoader.getTypesJson();
    }
}
