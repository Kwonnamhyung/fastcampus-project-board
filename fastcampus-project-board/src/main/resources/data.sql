insert into article(title , content , hashtag , created_at , created_by , modified_at , modified_by)
values ('test title' , 'test content' , 'test hashtag' , LOCALTIMESTAMP(0) , 'KNH' , LOCALTIMESTAMP(0) , 'KNH');

insert into article_comment(article_id , content , created_at , created_by , modified_at , modified_by)
values ((select id from article where article.title = 'test title') , 'test content', LOCALTIMESTAMP(0) , 'KNH' , LOCALTIMESTAMP(0) , 'KNH');

insert into article_comment(article_id , content , created_at , created_by , modified_at , modified_by)
values ((select id from article where article.title = 'test title') , 'suwon', LOCALTIMESTAMP(0) , 'KNH' , LOCALTIMESTAMP(0) , 'KNH');