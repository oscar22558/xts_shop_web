import { useState } from "react";

import {
  PaymentElement,
  useStripe,
  useElements
} from "@stripe/react-stripe-js";
import { Box, FormHelperText } from "@mui/material";

type Props = {
  paymentTotal: number
}
const CheckoutForm: React.FC<Props> = ({
  paymentTotal
})=> {
  const stripe = useStripe();
  const elements = useElements();
  const [message, setMessage] = useState<string|null|undefined>(null);
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    if (!stripe || !elements) {
      return;
    }

    setIsLoading(true);

    const { error } = await stripe.confirmPayment({
      elements,
      confirmParams: {
        return_url: "http://localhost/payment/confirmation",
      },
    });

    if (error.type === "card_error" || error.type === "validation_error") {
      setMessage(error.message);
    } else {
      setMessage("An unexpected error occurred.");
    }

    setIsLoading(false);
  };

  return (
    <form id="payment-form" onSubmit={handleSubmit}>
      <PaymentElement id="payment-element"/>
      {message && <FormHelperText error id="payment-message">{message}</FormHelperText>}
      <Box sx={{display: "flex", paddingY: "10px", justifyContent: "flex-end"}}>
        <button disabled={isLoading || !stripe || !elements} id="submit" style={{padding: "10px"}}>
          <span id="button-text">
            {isLoading ? "Payment processing..." : `Pay now (HKD $${paymentTotal})`}
          </span>
        </button>
      </Box>
    </form>
  );
}
export default CheckoutForm