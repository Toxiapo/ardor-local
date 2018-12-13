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

package nxt.ae;

import nxt.account.Account.AccountAsset;
import nxt.account.HoldingType;
import nxt.db.DbIterator;
import nxt.migration.HoldingSnapshot;

import java.util.Map;
import java.util.stream.Collectors;

class AssetSnapshot extends HoldingSnapshot {
    AssetSnapshot() {
        super(HoldingType.ASSET);
    }

    @Override
    protected Map<String, Long> takeSnapshot(long holdingId) {
        try (DbIterator<AccountAsset> accounts = Asset.getAsset(holdingId).getAccounts(0, -1)) {
            return accounts.stream()
                    .collect(Collectors.toMap(account -> Long.toUnsignedString(account.getAccountId()), AccountAsset::getQuantityQNT));
        }
    }
}
