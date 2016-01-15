
drop procedure if exists featureMatchups;

DELIMITER //
create procedure featureMatchups()
 BEGIN

	-- create squad_temp table
	create table squad_temp
    select distinct st.squad_id from standing st inner join squad s on  st.squad_id=s.id 
																		 inner join competition c on s.competition_id=c.id 
	where st.matchday=c.current_matchday-1 and st.current_position > 0 and st.current_position < 5  ;
	
	-- create matchup_temp table
	create table matchup_temp 
	select distinct m.id as id from matchup m inner join matchup_detail d on m.id=d.matchup_id and d.squad_id in (select squad_id from squad_temp);
	
	update matchup set featured=0 where id > 0;
	
	update matchup m inner join matchup_temp t on m.id=t.id
	set  m.featured=1;

	drop  table squad_temp;
	drop  table matchup_temp;

END //
 
DELIMITER ;