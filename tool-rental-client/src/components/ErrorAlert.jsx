import Alert from "./Alert";

const ErrorAlert = ({ url, description, data }) => {
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
export default ErrorAlert;
