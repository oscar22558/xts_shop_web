import { Dialog, DialogTitle, DialogContent, DialogContentText, DialogActions, Button } from "@mui/material"
import CreateAddressSection from "../../../SettingsPage/AddressPage/CreateAddressSection"
import AddressSelectionForm from "../AddressSelectionForm"

type Props = {
    isShown: boolean,
    onClose?: ()=>void
}
const AddressSelectionDialog: React.FC<Props> = ({isShown, onClose})=>{
   return (
        <Dialog open={isShown} onClose={onClose} maxWidth="md" fullWidth>
            <DialogTitle>Shipping Address</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Select where to ship the order.
                </DialogContentText>
                <AddressSelectionForm /> 
                <CreateAddressSection />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose} variant="contained" sx={{margin: "10px"}}>Confirm</Button>
            </DialogActions>
        </Dialog>
   ) 
}
export default AddressSelectionDialog