CREATE TABLE IF NOT EXISTS labeling_results
(
    id                 bigserial PRIMARY KEY,
    value              text NOT NULL,
    words              bigint[],
    labels             bigint[],
    manual_handle_date TIMESTAMP,
    trash              boolean
);

CREATE TABLE IF NOT EXISTS labeling_results_labels
(
    labeling_results_id bigint,
    label_id            bigint,
    primary key (labeling_results_id, label_id),
    foreign key (label_id) references labels (id),
    foreign key (labeling_results_id) references labeling_results (id)
);

CREATE TABLE IF NOT EXISTS labeling_results_cases
(
    labeling_results_id bigint,
    case_id             bigint,
    primary key (labeling_results_id, case_id),
    foreign key (case_id) references label_cases (id),
    foreign key (labeling_results_id) references labeling_results (id)
);