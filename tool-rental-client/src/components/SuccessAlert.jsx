import Alert from "./Alert";

const SuccessAlert = ({ url, description, data }) => {
    return (
        <Alert
            props={{
                triggerText: "",
                description,
                actionText: "OK",
                url,
                data,
            }}
        />
    );
};
export default SuccessAlert;
