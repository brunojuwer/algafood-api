alter table forma_pagamento add column data_atualizacao datetime null;
update forma_pagamento set data_atualizacao = UTC_TIMESTAMP;
alter table forma_pagamento modify column data_atualizacao datetime not null;