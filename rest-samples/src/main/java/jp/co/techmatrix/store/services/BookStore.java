package jp.co.techmatrix.store.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("/rest")
public class BookStore extends Application{
	
	/**
	 * コンストラクタ
	 * JAX-RS + Swagger の設定を施す。
	 */
	public BookStore(){
		// swaggerの設定
	    BeanConfig beanConfig = new BeanConfig(); 
	    
	    beanConfig.setVersion("0.0.1");
	    beanConfig.setSchemes(new String[] {"http"});
	    String hostname = "localhost";
	    
	    // WildFlyがlocalhostでしかリスンしない。解決するまでコメントアウト
//	    try{
//			hostname = InetAddress.getLocalHost().getHostName();
//		}catch(UnknownHostException e){
//			e.printStackTrace();
//		}
	    beanConfig.setHost(hostname + ":8080");
	    beanConfig.setBasePath("/rest-samples/rest");
	    beanConfig.setResourcePackage("jp.co.techmatrix.store.services.rest");
	    beanConfig.setScan(true);
	}

	/**
	 * Swaggerの設定(web.xmlでもできるが、コードで)
	 * @see javax.ws.rs.ApplicationPath#getClasses
	 */
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> resources = new HashSet<>();

		// ResourceClassの追加
		resources.add(jp.co.techmatrix.store.services.rest.CartResource.class);

		// Swagger の有効化
		resources.add(ApiListingResource.class);
		resources.add(SwaggerSerializers.class);

		return resources;
	}
}
