-- Q1
SELECT member.name FROM ((member INNER JOIN checkout_item ON member.id = checkout_item.member_id) INNER JOIN book ON book.id = checkout_item.book_id) WHERE book.title ="The Hobbit";
-- Q2
SELECT COUNT(DISTINCT name) FROM member INNER JOIN checkout_item ON member.id = checkout_item.member_id;
-- Q3
SELECT title FROM book LEFT JOIN checkout_item ON book.id = checkout_item.book_id WHERE checkout_item.book_id IS NULL;
UNION
SELECT title FROM movie LEFT JOIN checkout_item ON movie.id = checkout_item.movie_id WHERE checkout_item.movie_id IS NULL;
-- Q4
INSERT INTO book VALUES (11,'The Pragmatic Programmer');
INSERT INTO member VALUES (43,'Moritz Dueck');
INSERT INTO checkout_item VALUES (43,11,NULL);
SELECT member.name FROM ((member INNER JOIN checkout_item ON member.id = checkout_item.member_id) INNER JOIN book ON book.id = checkout_item.book_id) WHERE book.title ="The Pragmatic Programmer";
-- Q5
SELECT name FROM member INNER JOIN checkout_item ON member.id = checkout_item.member_id GROUP BY name HAVING COUNT(name)>1;
