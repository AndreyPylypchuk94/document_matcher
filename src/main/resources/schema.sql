CREATE TABLE IF NOT EXISTS labeling_results
(
    id                bigserial PRIMARY KEY,
    value             text NOT NULL,
    words             text,
    labels            text,
    completed_labels  text,
    label_category_id int,
    handle_date       TIMESTAMP,
    trash             boolean,
    foreign key (label_category_id) references label_categories (id)
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