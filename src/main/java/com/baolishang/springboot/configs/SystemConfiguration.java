package com.baolishang.springboot.configs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Created by will on 16/9/12.
 */

public class SystemConfiguration {
	private static Log log = LogFactory.getLog( SystemConfiguration.class );
    private static String FILE_NAME_SYS = "/application.properties"; //文件名
    private static String FILE_NAME = "/system.properties"; //文件名
    private static final Properties conf_PROPERTIES_SYS;
    private static final Properties conf_PROPERTIES;

  public static void setFileName(String fileName) {
    FILE_NAME = fileName;
  }

  static
    {
      conf_PROPERTIES_SYS = new Properties();
      InputStream stream_sys = SystemConfiguration.class.getResourceAsStream(FILE_NAME_SYS);

        try {
          conf_PROPERTIES_SYS.load( stream_sys );
          String active = conf_PROPERTIES_SYS.getProperty( "spring.profiles.active" );
          SystemConfiguration.setFileName("/system-"+active+".properties");
          log.info( "loaded properties from resource " + FILE_NAME_SYS + ": " + conf_PROPERTIES_SYS );
        }
        catch ( Exception e ) {
          log.error( "problem loading properties from " + FILE_NAME_SYS );
        }
        finally {
          try {
            stream_sys.close();
          }
          catch ( IOException ioe ) {
            log.error( "could not close stream on " + FILE_NAME_SYS, ioe );
          }
        }

    conf_PROPERTIES = new Properties();
    InputStream stream = SystemConfiguration.class.getResourceAsStream(FILE_NAME);
    if ( stream == null ) {
      log.warn( FILE_NAME + " not found" );
    }
    else {
      try {
        conf_PROPERTIES.load( stream );
        log.info( "loaded properties from resource " + FILE_NAME + ": " + conf_PROPERTIES );
      }
      catch ( Exception e ) {
        log.error( "problem loading properties from " + FILE_NAME );
      }
      finally {
        try {
          stream.close();
        }
        catch ( IOException ioe ) {
          log.error( "could not close stream on " + FILE_NAME, ioe );
        }
      }
    }
  }
  public static Properties getProperties()
  {
    return conf_PROPERTIES;
  }

  public static void save()
  {
    try {
      URL url = SystemConfiguration.class.getResource( FILE_NAME);
      String filePath = java.net.URLDecoder.decode( url.getFile(), "utf-8" );
      log.info( "saving properties to :" + filePath );
      FileOutputStream fout = new FileOutputStream( filePath );
      DataOutputStream out = new DataOutputStream( fout );
      conf_PROPERTIES.store( out, "系统保存" );
    }
    catch ( FileNotFoundException ex ) {
      log.warn( ex.getMessage() );
    }
    catch ( UnsupportedEncodingException ex ) {
      log.warn( ex.getMessage() );
    }
    catch ( IOException ex ) {
      log.warn( ex.getMessage() );
    }
  }
  public static String get( String key )
  {
    return conf_PROPERTIES.getProperty( key );
  }
  public static int getIntValue( String key )
  {
    return getIntValue( key, 0 );
  }
  public static int getIntValue( String key, int defaultValue )
  {
    String str = conf_PROPERTIES.getProperty( key );
    int value = 0;
    try {
      value = Integer.parseInt( str );
    }
    catch ( NumberFormatException ex ) {
      value = defaultValue;
    }
    return value;
  }
  public static void main( String[] args )
  {
    //String tmpAry[][] = getGxtvProductAry();
  }
}
