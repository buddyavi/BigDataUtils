BigDataUtils
============
Contains projects related to Big Data utility


MemcachePopulator
============
Project for populating in memory cache with data which is frequently used in big projects. There are times when we need mapping between 2 things for example student name and student_id in our project. To prevent the overhead of hitting mysql/any other database again and again we can retrieve everything at once and store it in the form of key value pair in an inmemory cache server . Then as and when required in different parts of our project we can do a get on key and retrieve the value.

MySql server details , user details and Memcache server details can be changed in properties file.


