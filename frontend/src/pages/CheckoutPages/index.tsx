import { Box, Stepper, Step, StepLabel, Button } from "@mui/material";
import { useState } from "react"
import { useNavigate } from "react-router-dom";
import AppRouteList from "../../routes/AppRouteList";
import CheckoutSteps from "./models/CheckoutSteps";
import OrderRecipientPage from "./OrderRecipientPage";
import PaymentPage from "./PaymentPage";

const checkoutSteps = CheckoutSteps

const CheckoutPages = ()=>{
    const navigate = useNavigate()
    const [step, setStep] = useState(0)
    const handleNextClick = ()=>{
        if(step < 1) setStep(step + 1)
    }

    const handlePreviousClick = ()=>{
        if(step > 0) setStep(step - 1)
        navigate(AppRouteList.cart)
    }
    return (
        <Box sx={{ width: '100%', height: "100%", display: "flex", flexDirection: "column", alignItems: "center" }}>
            <Stepper activeStep={step} sx={{height: "100px", width: "550px"}}>
                {checkoutSteps.map((label, index) => {
                const stepProps: { completed?: boolean } = {};
                const labelProps = {};
                return (
                    <Step key={label} {...stepProps}>
                        <StepLabel {...labelProps}>{label}</StepLabel>
                    </Step>
                );
                })}
            </Stepper>
            <Box display="flex" justifyContent="center">
                {step === 0 ? <OrderRecipientPage onNextClick={handleNextClick} onPreviousClick={handlePreviousClick}/> : undefined}
                {step === 1 ? <PaymentPage /> : undefined}
            </Box>
        </Box>
    )
}
export default CheckoutPages