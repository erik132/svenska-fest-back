DROP VIEW IF EXISTS simple_event_view;

CREATE VIEW simple_event_view AS
SELECT
	e.id,
	name,
	address,
	date_time,
	max_participants,
	count(ep.first_name) AS participant_count
FROM events e LEFT JOIN event_participants ep ON e.id=ep.event_id GROUP BY e.id, name, address, date_time, max_participants ORDER BY e.id;