CREATE TABLE IF NOT EXISTS matching_results
(
    id                 bigserial PRIMARY KEY,
    title              text NOT NULL,
    words              text[],
    types              text[],
    type               text,
    manual_handle_date TIMESTAMP
);