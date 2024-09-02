const makePostRequest = async (url, getData) => {
    // make api request with form data
    try {
        const res = fetch(url, {
            method: "POST",
            body: getData(),
        });
        // res.ok && console.log("Success rented tool", res.status);
        // setStatus(res.ok);
        // setApiResponse(await res.json());
        return res;
    } catch (error) {
        console.log(error);
        throw new Error(error.message);
    }
};

export default makePostRequest;
