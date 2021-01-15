package data;

public class DigitalSignature {
    private final byte[] firma;

    public DigitalSignature (byte[] firm){
        this.firma = firm;
    }

    public byte[] getSignature() {
        return firma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DigitalSignature signature = (DigitalSignature) o;
        return firma.equals(signature.firma);

    }



    @Override
    public String toString() {
        return "DigitalSignature Signature{" + "Signature: '" + firma.toString() + '\'' + '}';
    }
}

