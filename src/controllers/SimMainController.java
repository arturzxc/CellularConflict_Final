/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import core.Core;
import gui.MainFrame;

/**
 *
 * @author Artur Krzynowek
 */
public class SimMainController extends MainController {

    private Core model;
    private MainFrame view;


    public SimMainController(Core core, MainFrame f) {
        model = core;
        view = f;
    }

    @Override
    public void viewRender() {
        view.render();
    }

    @Override
    public void modelUpdate() {
        if (!Settings.getInstance().isPaused()) {
            model.update();
        }
    }

    @Override
    public void mainLoop() {
    }
}
