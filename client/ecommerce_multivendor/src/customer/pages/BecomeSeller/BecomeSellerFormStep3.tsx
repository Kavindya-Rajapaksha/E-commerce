import { TextField } from "@mui/material";
import React from "react";

function BecomeSellerFormStep3({formik}:any) {
  return (
    <div className="space-y-5">
      <TextField
        fullWidth
        name="bankDetails.accountNumber"
        label="Account number"
        value={formik.values.bankDetails?.accountNumber}
        onChange={formik.handleChange}
        error={formik.touched.bankDetails?.accountNumber && Boolean(formik.errors.bankDetails?.accountNumber)}
        helperText={formik.touched.bankDetails?.accountNumber && formik.errors.bankDetails?.accountNumber}
      />
      <TextField
        fullWidth
        name="bankDetails.ifscCode"
        label="IFSC Code"
        value={formik.values.bankDetails.ifscCode}
        onChange={formik.handleChange}
        error={formik.touched.bankDetails?.ifscCode && Boolean(formik.errors.bankDetails?.ifscCode)}
        helperText={formik.touched.bankDetails?.ifscCode && formik.errors.bankDetails?.ifscCode}
      />
      <TextField
        fullWidth
        name="bankDetails.accountHolderName"
        label="Account Holder Name"
        value={formik.values.bankDetails?.name}
        onChange={formik.handleChange}
        error={formik.touched.bankDetails?.name && Boolean(formik.errors.bankDetails?.name)}
        helperText={formik.touched.bankDetails?.name && formik.errors.bankDetails?.name}
      />
    </div>
  );
}

export default BecomeSellerFormStep3;
