import { Box, TextField } from "@mui/material";
import React from "react";

function BecomeSellerFormStep1({ formik }: any) {
  return (
    <Box>
      <p className="text-xl font-bold text-center pb-9">Contact Detail</p>
      <div className="space-y-9">
        <TextField
          fullWidth
          name="mobile"
          label="Mobile"
          value={formik.values.mobile}
          onChange={formik.handleChange}
          error={formik.touched.mobile && Boolean(formik.errors.mobile)}
          helperText={formik.touched.mobile && formik.errors.mobile}
        />
        <TextField
          fullWidth
          name="GSTIN"
          label="GSTIN"
          value={formik.values.GSTIN}
          onChange={formik.handleChange}
          error={formik.touched.GSTIN && Boolean(formik.errors.GSTIN)}
          helperText={formik.touched.GSTIN && formik.errors.GSTIN}
        />
        
      </div>
    </Box>
  );
}

export default BecomeSellerFormStep1;
