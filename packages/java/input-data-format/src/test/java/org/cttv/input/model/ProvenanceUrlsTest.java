package org.cttv.input.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProvenanceUrlsTest {

    @Before
    public void setUpProvenanceUrls() {
        provenanceUrls = new ProvenanceUrls();
    }

    @Test
    public void testAddLinkOutAndGetLinkouts() throws Exception {
        Assert.assertTrue("Original value for linkouts is null", provenanceUrls.getLinkouts() == null);
        Assert.assertFalse("Null parameter to addLinkOut method", provenanceUrls.addLinkOut(null));
        Assert.assertTrue("Valid parameter to addLinkOut method", provenanceUrls.addLinkOut(new LinkOut("Something nice", "http://something.org")));
        Assert.assertTrue("One element in the linkouts list", provenanceUrls.getLinkouts().size() == 1);
    }

    private ProvenanceUrls provenanceUrls;
}