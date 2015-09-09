package com.inshop.config;

import org.hibernate.HibernateException;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitEntityNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

/**
 * Created by savetisyan on 09/09/15.
 */
public class PrefixTableNameNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {
    private String prefix;

    public PrefixTableNameNamingStrategy(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Identifier determinePrimaryTableName(ImplicitEntityNameSource source) {
        if (source == null) {
            throw new HibernateException("Entity naming information was not provided.");
        }

        String tableName = prefix + source.getEntityNaming().getJpaEntityName().toLowerCase();
        return toIdentifier(tableName, source.getBuildingContext());
    }
}
