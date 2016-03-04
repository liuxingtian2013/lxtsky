package net.ilifang.app.commons.utils;

/**
 * 根据接口请求、解析、处理完成后的处理器，一般在onResult回调函数里面处理UI线程更新或者画面切换
 * 
 * @author bobby
 *
 * @param <Entity>
 *            接口返回结果填充的结果对象entity
 */
public interface ResultHandler<Entity> {

    /**
     * 返回接口的结果对象，默认需要你根据接口返回结果创建一个对象entity。当结果返回后需要在更新UI再使用handler跟均消息处理UI
     * 
     * @param entity
     */
    void onResult(Entity entity);

}
