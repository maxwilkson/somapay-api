ALTER TABLE folha_pagamento ALTER COLUMN taxa TYPE numeric(19,10) USING taxa::numeric;