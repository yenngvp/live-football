DELIMITER //

CREATE  PROCEDURE `update_matchup_time`(zone_offset INT)
begin

create table matchup_tmp (id INT primary key auto_increment, matchtime datetime);

insert into matchup_tmp select id, STR_TO_DATE(CONCAT(start_at, ' ', kickoff), '%Y-%m-%d %H:%i:%s') as matchtime
from matchup
where start_at is not null and kickoff is not null;

update matchup_tmp set matchtime = DATE_ADD(matchtime, INTERVAL zone_offset HOUR) where id > 0;

update matchup m inner join matchup_tmp t on m.id=t.id 
set m.start_at=DATE(matchtime), m.kickoff=TIME(matchtime)
where m.id>0;

drop table matchup_tmp;

end //

DELIMITER ;