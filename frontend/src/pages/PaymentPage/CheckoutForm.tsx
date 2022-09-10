import { useEffect, useState } from "react";

import {
  PaymentElement,
  useStripe,
  useElements
} from "@stripe/react-stripe-js";
import { Box } from "@mui/material";

type Props = {
  paymentTotal: number
  onUserFinishedPayment?: (isUserFinishedPayment: boolean)=>void
}
const CheckoutForm: React.FC<Props> = ({
  paymentTotal,
  onUserFinishedPayment
})=> {
  const stripe = useStripe();
  const elements = useElements();

  const [message, setMessage] = useState<string|null|undefined>(null);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    if (!stripe) {
      return;
    }

    const clientSecret = new URLSearchParams(window.location.search).get(
      "payment_intent_client_secret"
    );
    if (!clientSecret) {
      return;
    }
    stripe.retrievePaymentIntent(clientSecret).then(({ paymentIntent }) => {
      switch (paymentIntent?.status) {
        case "succeeded":
          setMessage("Payment succeeded!");
          onUserFinishedPayment && onUserFinishedPayment(true)
          break;
        case "processing":
          setMessage("Your payment is processing.");
          break;
        case "requires_payment_method":
          setMessage("Your payment was not successful, please try again.");
          break;
        default:
          setMessage("Something went wrong.");
          break;
      }
    })

  }, [stripe]);

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    if (!stripe || !elements) {
      return;
    }

    setIsLoading(true);

    const { error } = await stripe.confirmPayment({
      elements,
      confirmParams: {
        return_url: "http://localhost:3000",
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
      <Box sx={{display: "flex", paddingY: "10px", justifyContent: "flex-end"}}>
        <button disabled={isLoading || !stripe || !elements} id="submit" style={{padding: "10px"}}>
          <span id="button-text">
            {isLoading ? "Payment processing..." : `Pay now (HKD $${paymentTotal})`}
          </span>
        </button>
      </Box>
      {message && <div id="payment-message">{message}</div>}
    </form>
  );
}
export default CheckoutForm