ALTER TABLE candidates 
ADD COLUMN "file" bytea,
ADD COLUMN "file_mine" varchar(100),
ADD COLUMN "file_extension" varchar(5);
