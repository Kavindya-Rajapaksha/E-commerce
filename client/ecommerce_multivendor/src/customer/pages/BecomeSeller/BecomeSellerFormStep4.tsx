import { TextField } from "@mui/material";
import { AnyCnameRecord } from "dns";
import React from "react";

function BecomeSellerFormStep4({ formik }: any) {
  return (
    <div className="space-y-5">
      <TextField
        fullWidth
        name="businessDetail.businessName"
        label="Business Name"
        value={formik.values.businessDetail?.businessName}
        onChange={formik.handleChange}
        error={
          formik.touched.businessDetail?.businessName &&
          Boolean(formik.errors.businessDetail?.businessName)
        }
        helperText={
          formik.touched.businessDetail?.businessName &&
          formik.errors.businessDetail?.businessName
        }
      />
      <TextField
        fullWidth
        name="sellerName"
        label="Seller Name"
        value={formik.values.sellerName}
        onChange={formik.handleChange}
        error={formik.touched.sellerName && Boolean(formik.errors.sellerName)}
        helperText={formik.touched.sellerName && formik.errors.sellerName}
      />
      <TextField
        fullWidth
        name="email"
        label="Email"
        value={formik.values.email}
        onChange={formik.handleChange}
        error={formik.touched.email && Boolean(formik.errors.email)}
        helperText={formik.touched.email && formik.errors.email}
      />
      <TextField
        fullWidth
        name="password"
        label="Password"
        value={formik.values.password}
        onChange={formik.handleChange}
        error={formik.touched.password && Boolean(formik.errors.password)}
        helperText={formik.touched.password && formik.errors.password}
      />
    </div>
  );
}

export default BecomeSellerFormStep4;
