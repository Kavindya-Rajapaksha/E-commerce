import { Label } from "@mui/icons-material";
import { Button, Step, StepLabel, Stepper } from "@mui/material";
import React, { act, useState } from "react";
import BecomeSellerFormStep1 from "./BecomeSellerFormStep1";
import { useFormik } from "formik";
import BecomeSellerForm2 from "./BecomeSellerForm2";
import BecomeSellerFormStep3 from "./BecomeSellerFormStep3";
import BecomeSellerFormStep4 from "./BecomeSellerFormStep4";

function SellerAccountForm() {
  const steps = [
    "Tax Details & Mobile",
    "Pickup Address",
    "Bank Details",
    "Supplier Details",
  ];
  const [activeStep, setActiveStep] = useState(0);

  const handleStep = (value: number) => () => {
    (activeStep < steps.length - 1 || (activeStep > 0 && value == -1)) &&
      setActiveStep(activeStep + value);
    activeStep == steps.length - 1 && handleCreateAccount();
    console.log("active step", activeStep);
  };

  const handleCreateAccount = () => {
    console.log("Create account");
  };

  const formik = useFormik({
    initialValues: {
      mobile: "",
      otp: "",
      gstin: "",
      pickupAddress: {
        name: "",
        mobile: "",
        pincode: "",
        address: "",
        locality: "",
        city: "",
        state: "",
      },
      bankDetails: {
        accountNumber: "",
        ifscCode: "",
        accountHolderName: "",
      },
      sellerName: "",
      email: "",
      businessDetails: {
        businessName: "",
        businessEmail: "",
        businessMobile: "",
      },
      logo: "",
      banner: "",
      businessAddress: "",
      password: "",
    },
    onSubmit: (values: any) => {
      console.log(values, "formik submitted");
      console.log("active step", activeStep);
    },
  });
  return (
    <div>
      <Stepper activeStep={activeStep} alternativeLabel>
        {steps.map((label, index) => (
          <Step key={label}>
            <StepLabel>{label}</StepLabel>
          </Step>
        ))}
      </Stepper>
      <section className="mt-20 space-y-10">
        <div>
          {activeStep == 0 ? (
            <BecomeSellerFormStep1 formik={formik} />
          ) : activeStep == 1 ? (
            <BecomeSellerForm2 formik={formik} />
          ) : activeStep == 2 ? (
            <BecomeSellerFormStep3 formik={formik} />
          ) : activeStep == 3 ? (
            <BecomeSellerFormStep4 formik={formik} />
          ) : (
            ""
          )}
        </div>
        <div className="flex items-center justify-between">
          <Button
            onClick={handleStep(-1)}
            variant="contained"
            disabled={activeStep == 0}
          >
            Back
          </Button>

          <Button onClick={handleStep(1)} variant="contained">
            {activeStep == steps.length - 1 ? "Create Account" : "continue"}
          </Button>
        </div>
      </section>
    </div>
  );
}

export default SellerAccountForm;
