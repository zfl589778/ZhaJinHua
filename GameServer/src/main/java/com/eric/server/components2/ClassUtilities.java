package com.eric.server.components2;

/**
 * 批量加载指定包下的类
 * @author Administrator
 *
 */
public class ClassUtilities {
	private static org.slf4j.Logger logger = org.slf4j.LoggerFactory
			.getLogger(java.lang.invoke.MethodHandles.lookup().lookupClass().getSimpleName());

	public static Class<?> getClass(String PackagePrefix, String ClassName) {
		logger.trace("Called getClass " + PackagePrefix + ".***." + ClassName);
		Package[] packages = Package.getPackages();
		for (Package p : packages) {
			if (p.getName().startsWith(PackagePrefix)) {
				try {
					return Class.forName(p.getName() + "." + ClassName);
				} catch (ClassNotFoundException ignore) {
				}
			}
		}
		logger.warn("Class " + ClassName + " Not Find in Package " + PackagePrefix);
		return null;
	}
}
