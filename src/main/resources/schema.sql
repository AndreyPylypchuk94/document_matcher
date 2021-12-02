CREATE TABLE IF NOT EXISTS matching_results
(
    id                 bigserial PRIMARY KEY,
    title              text NOT NULL,
    words              text[],
    types              text[],
    manual_handle_date TIMESTAMP,
    trash              boolean
);

CREATE TABLE IF NOT EXISTS matching_results_types
(
    matching_results_id bigint,
    type_id             bigint,
    primary key (matching_results_id, type_id),
    foreign key (type_id) references document_types (id),
    foreign key (matching_results_id) references matching_results (id)
);

CREATE TABLE IF NOT EXISTS matching_results_cases
(
    matching_results_id bigint,
    case_id             bigint,
    primary key (matching_results_id, case_id),
    foreign key (case_id) references matching_cases (id),
    foreign key (matching_results_id) references matching_results (id)
);