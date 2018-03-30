package com.bola.nwcl.web;

import com.bola.nwcl.common.tomcat.TomcatBootstrapHelper;

public class TomcatBootStrap {
	public static void main(String[] args) {

		new TomcatBootstrapHelper(8081, false, "dev").start();

	}
}
