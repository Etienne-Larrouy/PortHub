package PortHub.WebBrowser;

import com.teamdev.jxbrowser.Browser;
import com.teamdev.jxbrowser.BrowserFactory;
import com.teamdev.jxbrowser.BrowserType;
import javax.swing.*;
import java.awt.*;

public class BrowserSample 
{    
	public static void main(String[] args) 
	{        Browser browser = BrowserFactory.createBrowser(BrowserType.Mozilla);         
			 JFrame frame = new JFrame();
			 frame.add(browser.getComponent(), BorderLayout.CENTER);
			 frame.setSize(700, 500);
			 frame.setLocationRelativeTo(null);
			 frame.setVisible(true);
			 browser.navigate("http://www.google.com");    
	}
}
