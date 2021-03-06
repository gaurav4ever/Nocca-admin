package com.bookpurple.pp1.dagger.provider;

import com.bookpurple.pp1.dagger.component.Graph;

/*
 * Written by Gaurav Sharma on 2020-02-01.
 */
public class ComponentProvider {

    private static Graph component;

    private static void initializeGraph() {
        component = Graph.Initializer.initialize();
    }

    public static Graph getComponent() {
        if (component == null) {
            initializeGraph();
        }
        return component;
    }
}
