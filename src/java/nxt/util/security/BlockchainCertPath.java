package nxt.util.security;

import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlockchainCertPath extends CertPath {

    private final List<Certificate> certificates = new ArrayList<>();

    public BlockchainCertPath(Certificate certificate) {
        super("Blockchain");
        certificates.add(certificate);
    }

    @Override
    public Iterator<String> getEncodings() {
        return null;
    }

    @Override
    public byte[] getEncoded() {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte[] getEncoded(String encoding) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<? extends Certificate> getCertificates() {
        return certificates;
    }
}
