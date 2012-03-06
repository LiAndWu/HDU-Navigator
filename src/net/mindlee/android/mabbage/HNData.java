package net.mindlee.android.mabbage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;

public class HNData {
	
	static Activity activity;

	public static void Initiate(Activity activity) {
		HNData.activity = activity;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Element> GetDistanceOfArea(String NodeName) {
	    try  {
			SAXBuilder saxBuilder = new SAXBuilder();
	    	FileInputStream fileInputStream  = new FileInputStream("/data/data/net.mindlee.android.mabbage/files/hdunav.xml");
	    	Document document = saxBuilder.build(fileInputStream);
	    	fileInputStream.close();
	    	Element HDUNav = document.getRootElement();
	    	Element Node = HDUNav.getChild(NodeName);
	    	Element Distance = Node.getChild("Distance");
	    	return Distance.getChildren();
	    } 
	    catch (IOException e1) {
	    	e1.printStackTrace();
		} 
	    catch (JDOMException e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Element> GetShownNameOfArea(String NodeName) {
	    try  {
			SAXBuilder saxBuilder = new SAXBuilder();
	    	FileInputStream fileInputStream  = new FileInputStream("/data/data/net.mindlee.android.mabbage/files/hdunav.xml");
	    	Document document = saxBuilder.build(fileInputStream);
	    	fileInputStream.close();
	    	Element HDUNav = document.getRootElement();
	    	Element Node = HDUNav.getChild(NodeName);
	    	Element Distance = Node.getChild("ShownName");
	    	return Distance.getChildren();
	    } 
	    catch (IOException e1) {
	    	e1.printStackTrace();
		} 
	    catch (JDOMException e) {
			e.printStackTrace();
		}
	    return null;
	}
	public static void CheckXMLExistence() {
        try  {
			FileInputStream fileInputStream = new FileInputStream("/data/data/net.mindlee.android.mabbage/files/hdunav.xml");
			fileInputStream.close();
		} 
        catch (FileNotFoundException e) {
        	AssetManager assets = activity.getAssets();
        	try {
				FileOutputStream fileOutputStream =activity.openFileOutput("hdunav.xml",Context.MODE_PRIVATE);
				InputStream fileInputStream = assets.open("hdunav.xml");

				byte[] buffer = new byte[1024];
				int length = -1;
				while((length = fileInputStream.read(buffer)) != -1 ) {
				    fileOutputStream.write(buffer, 0, length);
				}

				fileOutputStream.flush();
				fileOutputStream.close();
			} 
        	catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}    			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
