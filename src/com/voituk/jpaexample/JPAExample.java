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
 * 1. READ UNCOMMITTED  ( изоляция уровня чтения неподтвержденного )
 *    Указывает что другие транзакции могут читать строки-данные которые были изменены этой-текущей транзакцией (инструкцией) но еще не были зафиксированы.
 * 2. READ COMMTITED  ( изоляция уровня чтения подтвержденного )
 *    Указывает что другие транзакции НЕмогут читать строки-данные которые были изменены этой-текущей транзакцией (инструкцией) но еще не были зафиксированы.
 * 3. REPEATABLE READ  ( изоляция уровня повторяемого чтения )
 *    Указывает что другие транзакции НЕмогут читать строки-данные которые были изменены этой-текущей транзакцией (инструкцией) но еще не были зафиксированы.
 *    И другие транзакции НЕмогут изменять строки-данные читаемые этой-текущей транзакцией до ее завершения.
 * SNAPSHOT
 * 4. SERIALIZABLE  ( упорядоченная изоляция )
 *    Указывает что другие транзакции НЕмогут читать строки-данные которые были изменены этой-текущей транзакцией (инструкцией) но еще не были зафиксированы.
 *    И другие транзакции НЕмогут изменять строки-данные читаемые этой-текущей транзакцией до ее завершения.
 *    И другие транзакции НЕмогут вставлять новые строки-данные со значениями ключа которые входят в диапазон ключей ДЛЯ считываемых инструкциями этой-текущей транзакции до ее завершения.
 *    
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *                                                             (Spring Roo in Under Nine Minutes) https://www.youtube.com/watch?v=K78vL72XDqw
 * (По следам Spring Pet Clinic. Maven/ Spring Context/ Spring Test/ Spring ORM/ Spring Data JPA) http://habrahabr.ru/post/232381/
 * 
 *                   (java persistence api что это)
 *           (Introduction to Java Persistence API) http://voituk.kiev.ua/2008/01/30/introduction-to-java-persistence-api/
 *             (Java Persistence API (JPA): Основы) http://berkut.homelinux.com/download/pdf/jpa_basic_rus.pdf
 * (SET TRANSACTION ISOLATION LEVEL (Transact-SQL)) https://msdn.microsoft.com/ru-ru/library/ms173763%28v=sql.120%29.aspx
 * 
 *                                                            (jpa persistence стратегии)
 **                 (Шпаргалка Java программиста 1: JPA и Hibernate в вопросах и ответах) http://habrahabr.ru/post/265061/
 * (Alex Tretyakov Blog ** JPA-маппинг иерархии классов с помощью Single-table стратегии) http://alextretyakov.blogspot.com/2013/11/jpa-mapping-ierarhii-klassov-s-pomoshju.html
 *       (Alex Tretyakov Blog ** JPA-маппинг иерархии классов с помощью Joined стратегии) http://alextretyakov.blogspot.com/2013/11/jpa-joined-strategija.html
 * 
 * (Persistence.createEntityManagerFactory)
 *   (Начало работы с Java Persistence API) http://www.tune-it.ru/web/vnik/home/-/blogs/начало-работы-с-java-persistence-api
 * 
 *                               (уровни изоляции как работают)
 ** (Технологии баз данных: SQL, T-SQL, PL/SQL, реляционные БД) http://datasql.ru/basesql/16.htm
 *                          (MySQL: уровни изоляции транзакций) http://habrahabr.ru/post/135217/
 *                (Уровни изоляции транзакций в SQL. Шпаргалка) http://www.arbinada.com/main/ru/node/619
 * 
 *                                  (Введение в хеш-таблицы) https://bitsofmind.wordpress.com/2008/07/28/introduction_in_hash_tables/
 * (PowerMock (+Mockito): новый взгляд на unit-тестирование) http://habrahabr.ru/post/172239/
 *                      (Тестирование GWT с помощью Mockito) http://dmitrynikol.blogspot.com/2011/10/gwt-mockito.html
 * + + + + + + + + + + + + + + + + + + + + + + + + + + + +
 *                                             (Уровни изоляции) http://professorweb.ru/my/sql-server/2012/level3/3_16.php | https://books.google.com.ua/books?id=yqW0D1U0Q9IC&pg=PA343&lpg=PA343&dq=грязное+чтение+неповторяемое+чтение+чтение+фантомов&source=bl&ots=Hc3e5tQSpr&sig=c49sASAVEk_sh5xH1o2DoEaXusc&hl=ru&sa=X&ved=0ahUKEwj3nKO3r8zJAhXBFiwKHeh8CH8Q6AEIMjAE#v=onepage&q=грязное чтение неповторяемое чтение чтение фантомов&f=false
 *                                  (Уровни изоляции транзакции) https://www.youtube.com/watch?v=ovG4QXNpu7U
 *                (Уровень изоляции READ COMMITED SNAPSHOT в 1С) https://www.youtube.com/watch?v=D8PHwf5RHB4
 * (Уровень изоляции неповторяемое чтение, Неповторяемое чтение) https://www.youtube.com/watch?v=TWTDFWxSRYs
 *                          (Уровень изоляции Serializable в 1С) https://www.youtube.com/watch?v=TAGNnvCghlY
 *   (Технологии баз данных: SQL, T-SQL, PL/SQL, реляционные БД) http://datasql.ru/basesql/16.htm
 * 
 * Транзакции - защищают данные с которыми работает пользователь чтобы другой пользователь их неиспортил.
 * Но существуют ситуации когда данные ненужно защищать (потому-что они неявляются важными)...
 * Поэтому одни данные являются очень важными и их нужно защищать, а другие данные неявляются важными или имеют среднюю важность их можно выборочно защищать.
 * Уровень изоляции транзакции - это набор правил которые определяют насколько сильно нужно защищать данные (в транзакциях).
 * 
 * > В ходе выполнения транзакции результат выполнения может быть либо применен-зафиксирован либо отменен-откат.
 * Так вот, пока транзакция еще небыла зафиксирована - нет никакой гарантии что может быть откат - и все незафиксированные данные пропадут - это "Грязное чтение"
 * 
 * > В ходе выполнения транзакции читаем каждую строку (если их много) с какой-то последовательностью.
 * И пока читаем эту строку - она заблокирована от изменения, но как только закончили ее читать и перешли к другой, тогда другая транзакция может изменить освободившуюся строку.
 * То есть нет никаких гарантий что между чтением строки и фиксированием транзакции эта строка не будет снова изменена - это "Неповторяемое чтение".
 * 
 * > В ходе выполнения транзакции также читаем строки (если их много) с какой-то последовательностью...
 * И в этот момент другая транзакция вставляет новые строки между читаемыми строками уже после их прочтения (здесь нет блокировок на еще отсутствующие строки).
 * То есть нет никаких гарантий что после прочтения всех строк до момента фиксированием транзакции не будет добавления новых строк - это "Чтение фантомов".
 * 
 * 1. READ UNCOMMITTED (нет защиты, существуют все 3-и проблемы: "Грязное чтение","Неповторяемое чтение","Чтение фантомов")
 * 2. READ COMMTITED   (решается проблема "Грязного чтения")
 * 3. REPEATABLE READ  (решается проблема "Неповторяемого чтения")
 * 4. SERIALIZABLE     (решается проблема "Чтения фантомов")
 * 
 * (Базы данных. Вводный курс) http://citforum.ck.ua/database/advanced_intro/80.shtml
 * (Уровни изоляции транзакций в SQL) http://www.osp.ru/pcworld/2009/07/9708191/
 * (Блокировки и уровни изоляции транзакций InnoDB в MySQL) http://habrahabr.ru/post/238513/
 * http://shurshun.ru/tranzaktsii-blokirovki-urovni-izoliovannosti-tranzaktsiy-v-mysql/
 * http://kek.ksu.ru/EOS/BD/SQL_transaction.html
 * http://www.sql.ru/forum/699859/izuchau-urovni-izolyacii-tranzakciy-v-sql-neponyatnoe-povedenie-read-committed
 * http://habrahabr.ru/post/135217/
 * http://www.arbinada.com/main/ru/node/619
 * (уровни изоляции транзакций hibernate)
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
