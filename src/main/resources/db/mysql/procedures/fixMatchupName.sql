
drop procedure if exists fixMatchupName;

DELIMITER //

create procedure fixMatchupName() 
begin
		drop  table if exists matchup_temp ;
		create  table matchup_temp 
		select mid, did, s.name from
		(select  m.id mid,  d.id did, squad_id
		from matchup m inner join matchup_detail d on m.id=d.matchup_id)  t
			 inner join squad s on t.squad_id=s.id
		order by mid;
		
		update matchup m 
		inner join (
			select m1.mid, CONCAT(m1.name, ' vs ', m2.name) name from matchup_temp m1 inner join matchup_temp m2 on m1.mid=m2.mid and m1.did<m2.did 
		) t on m.id=t.mid
		set m.name = t.name;
			
		drop  table if exists matchup_temp ;

end //

DELIMITER ;

