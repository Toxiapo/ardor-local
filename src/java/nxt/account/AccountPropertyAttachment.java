/*
 * Copyright © 2013-2016 The Nxt Core Developers.
 * Copyright © 2016-2018 Jelurida IP B.V.
 *
 * See the LICENSE.txt file at the top-level directory of this distribution
 * for licensing information.
 *
 * Unless otherwise agreed in a custom licensing agreement with Jelurida B.V.,
 * no part of this software, including this file, may be copied, modified,
 * propagated, or distributed except according to the terms contained in the
 * LICENSE.txt file.
 *
 * Removal or modification of this copyright notice is prohibited.
 *
 */

package nxt.account;

import nxt.NxtException;
import nxt.blockchain.Attachment;
import nxt.blockchain.TransactionType;
import nxt.util.Convert;
import org.json.simple.JSONObject;

import java.nio.ByteBuffer;

public final class AccountPropertyAttachment extends Attachment.AbstractAttachment {

    private final String property;
    private final String value;

    AccountPropertyAttachment(ByteBuffer buffer) throws NxtException.NotValidException {
        super(buffer);
        this.property = Account.PROPERTY_NAME_RW.readFromBuffer(buffer).trim();
        this.value = Account.PROPERTY_VALUE_RW.readFromBuffer(buffer).trim();
    }

    AccountPropertyAttachment(JSONObject attachmentData) {
        super(attachmentData);
        this.property = Convert.nullToEmpty((String) attachmentData.get("property")).trim();
        this.value = Convert.nullToEmpty((String) attachmentData.get("value")).trim();
    }

    public AccountPropertyAttachment(String property, String value) {
        this.property = property.trim();
        this.value = Convert.nullToEmpty(value).trim();
    }

    @Override
    protected int getMySize() {
        return Account.PROPERTY_NAME_RW.getSize(property) + Account.PROPERTY_VALUE_RW.getSize(value);
    }

    @Override
    protected void putMyBytes(ByteBuffer buffer) {
        Account.PROPERTY_NAME_RW.writeToBuffer(property, buffer);
        Account.PROPERTY_VALUE_RW.writeToBuffer(value, buffer);
    }

    @Override
    protected void putMyJSON(JSONObject attachment) {
        attachment.put("property", property);
        attachment.put("value", value);
    }

    @Override
    public TransactionType getTransactionType() {
        return AccountPropertyTransactionType.ACCOUNT_PROPERTY_SET;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }

}
