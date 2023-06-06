insert into user_account(user_id , user_password , nickname , email , memo , created_at , created_by , modified_at , modified_by)
values ('knh' , '1234' , 'KNH' , 'knh@gmail.com' , 'I am knh' , now() , 'knh' , now() , 'knh');

insert into article(user_account_id ,title , content , hashtag , created_at , created_by , modified_at , modified_by)
values (1 , 'test title' , 'test content' , 'test hashtag' , LOCALTIMESTAMP(0) , 'KNH' , LOCALTIMESTAMP(0) , 'KNH');

insert into article_comment(user_account_id , article_id , content , created_at , created_by , modified_at , modified_by)
values (1,(select id from article where article.title = 'test title') , 'test content', LOCALTIMESTAMP(0) , 'KNH' , LOCALTIMESTAMP(0) , 'KNH');

insert into article_comment(user_account_id ,article_id , content , created_at , created_by , modified_at , modified_by)
values (1,(select id from article where article.title = 'test title') , 'suwon', LOCALTIMESTAMP(0) , 'KNH' , LOCALTIMESTAMP(0) , 'KNH');

