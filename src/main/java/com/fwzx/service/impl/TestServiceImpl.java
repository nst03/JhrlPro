package com.fwzx.service.impl;

import com.fwzx.pojo.SMSCode;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/*
	可以在接口类上的注解
	@CacheConfig(cacheNames = "activeTokenCache")，
	指定了缓存的名称为activeTokenCache,
	对应ehcache.xml中配置的名称：
	<cache name="activeTokenCache">
	由于在类上面声明的注解，
	所以类的所有带有@Cacheable、@CachePut、@CacheEvict注解的
	接口都使用activeTokenCache这个缓存，类里面的方法则可以不必在设置value值
	下面的接口可以不再特别说明这个注解。
*/
@Service
public class TestServiceImpl {

	/*
	@CachePut只是用来缓存结果的，
		并不会从缓存中查询数据，
		符合更新用户接口的逻辑，
		当用户更新接口被调用时，
		这个接口一定会被执行，
		同时把接口返回的结果缓存，
		这样调用查询指定用户接口，
		就能直接从缓存中返回更新过的用户。

		注意@CachePut注解的方法一定要有返回值，
		否则框架不知道缓存什么数据。
	 */
	@CachePut(value = "activeTokenCache", key = "#token")
	public SMSCode setToken(String token, String code) {
		SMSCode sms = new SMSCode();
		sms.setTele(token);
		sms.setCode(code);
		return sms;
	}


	/*
	@Cacheable表示先从缓存中查询，
		如果查询到就直接返回对应数据，
		如果查询不到就进入方法内部，
		当方法返回数据时，
		把对应的数据放到缓存中，
		以备下次查询使用。
		value表示指定使用缓存的名称，对应ehcache.xml中配置的名称：<cache name="activeTokenCache">
		key表示向指定key中缓存数据;  "targetClass.getName()+token + #token"; #后面是变量，前面是固定的字符串
	 */
	@Cacheable(value = "activeTokenCache", key = "#token")
	public SMSCode getToken(String token) {
		return null;
	}


	/*
	@CacheEvict是用来清除缓存的注解，
		使用效果是当删除Token时，
		同时删除缓存中的数据。

		另外通过设置@CachEvict的allEntries=true，
		调用这个接口时，
		会删除缓存activeTokenCache中的所有数据，
		而不单单是删除指定key的缓存。
	*/
	@CacheEvict(value = "activeTokenCache", key = "#token")
	public void delToken(String token) {

	}


//	@CachePut(value = "activeTokenCache", key = "#token")
//	public String setToken(String token, String code) {
//		return code;
//	}
//
//	@Cacheable(value = "activeTokenCache", key = "#token")
//	public String getToken(String token) {
//		return null;
//	}


}