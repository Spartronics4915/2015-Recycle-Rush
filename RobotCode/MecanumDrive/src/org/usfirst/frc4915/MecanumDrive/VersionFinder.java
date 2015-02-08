package org.usfirst.frc4915.MecanumDrive;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class VersionFinder {
	
	public static final String VERSION_ATTRIBUTE = "Code-Version";
	
	/**
	 * 
	 * Attempt to read the version manifest attribute output by our
	 * Ant build script directly into any compiled jar files.
	 *
	 * @return Parsed version, or null if not found or invalid.
	 *
	 */
	public static String parseVersionFromManifest(Object object) {
		Attributes attrs = findManifest(object).getMainAttributes();
		String version = attrs.getValue(VERSION_ATTRIBUTE);
		return version;
	}

	/**
	 * 
	 * Attempt to read manifest from <code>object</code>'s classloader.
	 * 
	 * <p>
	 * http://stackoverflow.com/a/1273196
	 * </p>
	 * 
	 * @param object Object to read classloader from
	 * @return The manifest, or null
	 */
	private static Manifest findManifest(Object object) {

		URLClassLoader cl = (URLClassLoader) object.getClass().getClassLoader();
		try {
		  URL url = cl.findResource("META-INF/MANIFEST.MF");
		  Manifest manifest = new Manifest(url.openStream());
		  return manifest;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

}
