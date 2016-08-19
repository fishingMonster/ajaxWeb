package com.PracticeWeb.util;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memorizer<A, V> implements Computable<A, V> {

	private final ConcurrentMap<A, Future<V>> cache=new ConcurrentHashMap<A, Future<V>>(); 
	private final Computable<A, V> compute;
	public Memorizer(Computable<A, V> compute) {
		this.compute=compute;
	}
	@Override
	public V compute(final A arg) throws InterruptedException {
		while(true)
		{
			Future<V> f=cache.get(arg);
			if(f==null)
			{
				Callable<V> eval=new Callable<V>() {
					public V call() throws InterruptedException {
						return compute.compute(arg);
					}
				};
				FutureTask<V> ft=new FutureTask<V>(eval);
				f=cache.putIfAbsent(arg, ft);
				if(f==null)
				{
					f=ft;
					ft.run();
				}
				try {
					return f.get();
				} catch (CancellationException e) {
					cache.remove(arg,f);
				}catch (ExecutionException e) {

				}
			}
		}
	}

}
