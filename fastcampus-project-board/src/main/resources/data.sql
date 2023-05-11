insert into article(title , content , hashtag) values ('test title' , 'test content' , 'test hashtag');

insert into article_comment(article_id , content) values ((select id from article where article.title = 'test title') , 'test content');