package com.bookpurple.pp1.dagger.component;

import com.bookpurple.pp1.dagger.module.MainModule;
import com.bookpurple.pp1.dagger.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/*
 * Written by Gaurav Sharma on 2020-02-01.
 */
@Singleton
@Component(modules = {MainModule.class, NetworkModule.class})
public interface Graph extends ModuleComponent {

    final class Initializer {
        public static Graph initialize() {
            return DaggerGraph.builder().build();
        }
    }
}
