insert into dictionary
values (1, 'ukr words')
on CONFLICT do nothing;

insert into dictionary
values (2, 'pl words')
on CONFLICT do nothing;

