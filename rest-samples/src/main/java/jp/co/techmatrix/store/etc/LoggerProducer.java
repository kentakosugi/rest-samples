package jp.co.techmatrix.store.etc;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 * Loggerをインジェクションするためのクラス
 * @author kosugi
 *
 */
@Dependent
public class LoggerProducer{
	/**
	 * InjectionPoint
	 */
	@Inject
	private InjectionPoint point;
	
	/**
	 * Loggerを提供するメソッド
	 * @return インジェクションされる対象のクラスに応じたロガーを返却する。
	 */
	@Produces
	public Logger getLogger(){
		return Logger.getLogger(this.point.getMember().getDeclaringClass().getName());
	}
}
