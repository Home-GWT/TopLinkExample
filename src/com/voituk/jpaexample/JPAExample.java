package com.voituk.jpaexample;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * (java persistence api что это)
 * http://voituk.kiev.ua/2008/01/30/introduction-to-java-persistence-api/
 * http://berkut.homelinux.com/download/pdf/jpa_basic_rus.pdf
 * https://msdn.microsoft.com/ru-ru/library/ms173763%28v=sql.120%29.aspx
 * 
 * (jpa persistence стратегии)
 * http://habrahabr.ru/post/265061/
 * http://alextretyakov.blogspot.com/2013/11/jpa-mapping-ierarhii-klassov-s-pomoshju.html
 * http://alextretyakov.blogspot.com/2013/11/jpa-joined-strategija.html
 * 
 * (Persistence.createEntityManagerFactory)
 * http://www.tune-it.ru/web/vnik/home/-/blogs/начало-работы-с-java-persistence-api
 * https://bitsofmind.wordpress.com/2008/07/28/introduction_in_hash_tables/
 * http://habrahabr.ru/post/172239/
 * http://dmitrynikol.blogspot.com/2011/10/gwt-mockito.html
 * 
 * *******************************************************
 * JDBC-интерфейс являеться стандартным способом при работе с базами данных.
 * Но JDBC-интерфейс имеет имеет недостатки:
 * - реализация клиентской стороны включая и драйвер коннекта к базе ложиться исключительно на плечи разработчика...
 * - драйвер коннекта к базе сильно зависит от Операционной Сиситемы и железа на стороне клиента где его используют...
 * - разработчику который использует этот JDBC-интерфейс приходиться самостоятельно реализовывать JAVA-класы (сущности) которые моделируют таблицы базы данных...
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JPA (Java Persistence API) - являеться ORM-фреймворком который решает эти задачи и упрощает жизнь разработчику:
 * - кросс-платфоорменная (независимая) реализация драйвера коннекта к базе!
 * - реализует работу с таблицами базы данных на уровне классов!
 * - и собстввенно все транзакции к базе выполняются под управлением исключительно ORM-фреймворка (так что разработчик НЕпариться над порядком построения транзакции и контролирование дедлоков)!
 * 
 * *******************************************************
 * Итак, есть база данных и есть клиенты...
 * Клиенты делают чтение и запись данных в базу...
 * Для выполнения (клиентских) транзакций к базе клиенту нужно подключиться к этой базе...
 * При подключение клиента к базе создается сессия в рамках которой выполняются все транзакций...
 * Каждая транзакция на изменение должна иметь подтверждение (как факт правильности инструкций и начало выполнения)...
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Итак:
 * - существует много клиентских коннектов к базе - сессий;
 * - существует активная инструкция (которая выполняется в текущий момент)...;
 * - и существуют другие инструкции (НЕактивные)...;
 * Дело в том что в базе данные являются общими, то есть, одновременно эти данные могут изменять много клиентов И при этом повредить данные которые еще НЕзафиксированы...
 * Чтобы избежать случаев когда несколько клиентов пытаются одновременно изменить одни и те-же данные И избежать ошибок - для этого и существуют "уровни изоляции":
 */

public class JPAExample {
	
	@SuppressWarnings("unchecked")
	public static void main(String... argv) {
		EntityManagerFactory factory = null;
		EntityManager        manager = null;
		
		try {
			factory = Persistence.createEntityManagerFactory("TestStore");
			manager = factory.createEntityManager();
			
			//Add several blogPosts entities to database using EntityManager
			manager.getTransaction().begin();
			manager.persist( new BlogPost("BlogPost 1", "This is first blog post", new Date(), true) );
			manager.persist( new BlogPost("Just another blog post", "This is second blog post", new Date(), true) );
			manager.getTransaction().commit();
			
			//Read all entities from database using EntityManager
			Query query = manager.createQuery("SELECT obj FROM BlogPost AS obj ORDER BY obj.date DESC");
			List<BlogPost> list = (List<BlogPost>) query.getResultList();
			
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				BlogPost blogPost = (BlogPost) iterator.next();
				System.out.println(blogPost.getId() + " :: '" + blogPost.getTitle() + "' >> " + blogPost.getBody() + " [" + blogPost.getDate() + "]");
			}
			
			
		} finally {
			if (manager!=null) manager.close();
			if (factory!=null) factory.close();
		}
		
		
	}

}
