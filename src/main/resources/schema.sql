CREATE TABLE IF NOT EXISTS coffee_makers (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL
    firm VARCHAR(35) NOT NULL,
    model VARCHAR(35) NOT NULL,
    milkCompartment INTEGER NOT NULL,
    sugarCompartment INTEGER NOT NULL,
    waterCompartment INTEGER NOT NULL,
    orderTiming INTEGER NOT NULL,
    flushingTiming INTEGER NOT NULL,
    orderTiming INTEGER NOT NULL,
    orderFlushingCount INTEGER NOT NULL,
    turned_on BOOLEAN NOT NULL,
    amount_of_water_for_flushing INTEGER NOT NULL
 );

